<template>
    <div class="container">
        <div class="row">
            <div v-for="producto in productosC" class="col-xs-12 col-md-6 col-lg-3 my-3">
                <div class="card">
                    <img :src="images + producto.id + '.jpg'" class="card-img-top" alt="...">
                    <div class="card-body d-grid ">
                        <h5 class="card-title mb-0">{{ producto.descripcion }}</h5>
                        <ul class="mb-0">
                            <li class="card-text">Talla{{ ' ' + producto.talla }}</li>
                            <li class="card-text">Precio: $ {{ producto.precio }}</li>
                        </ul>
                        <button class="btn btn-dark" @click="agregarCarrito(producto.id, producto.descripcion,
                        producto.talla, producto.precio, producto.categoria)">COMPRAR</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            titulo: "Catalogo",
            url: 'http://150.136.93.126:8080/tiendaropa/api/productos',
            urlCarrito: 'http://150.136.93.126:8080/tiendaropa/api/carrito/agregar/' + sessionStorage.getItem('correo'),
            productosC: [],
            images: "./src/assets/images/",
            correo: sessionStorage.getItem('correo'),
            token: sessionStorage.getItem('token')
        }
    },
    methods: {
        consultarProductos() {
            const opciones = {
                method: 'GET',
                headers: {
                    'Content-Type': "application/json",
                }
            };
            console.log("Vamos a guardar un producto en el carrito: ");
            console.log(sessionStorage.getItem('correo'));
            fetch(this.url, opciones).then(async (response) => {
                if (response.ok) {
                    this.productosC = await response.json();
                }
                else {
                    const error = new Error(response.statusText);
                    error.json = response.json();
                    console.log(error.message);
                    throw error;
                }
            })
        },
        agregarCarrito(id, descripcion, talla, precio, categoria) {
            console.log("vamos a  guardar el siguiente producto:");
            console.log(id);
            console.log(descripcion);
            console.log(talla);
            const opciones = {
                method: 'POST',
                headers: {
                    'Content-Type': "application/json",
                    'Authorization': "Bearer " + sessionStorage.getItem('token')
                },
                body: JSON.stringify({
                    id: 0,
                    correo: this.correo,
                    productos: [
                        {
                            id: id,
                            descripcion: descripcion,
                            talla: talla,
                            precio: precio,
                            categoria: categoria,
                        }
                    ],
                    estado: 'Creado'
                })
            };
            fetch(this.urlCarrito, opciones).then(async (response) => {
                console.log("Respuesta del servidor: "+response.status);
                if (response.ok) {

                    const respuesta = await response.json();
                    console.log(respuesta);
                } else {
                    const error = new Error(response.statusText);
                    error.json = response.json();
                    console.log(error.message);
                    throw error;
                }
            })
        }
    },
    beforeMount() {
        this.consultarProductos();
    }
}
</script>
<style>

</style>