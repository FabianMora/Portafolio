<template>
    <div>
        <div class="row">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <div class="col-3 text-center">
                        <RouterLink class="navbar-brand nombreTienda" to="/">{{ nombreTienda }}</RouterLink>
                    </div>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="col-6 collapse navbar-collapse justify-content-center" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <RouterLink class="nav-link active" to="/catalogo">Productos</RouterLink>
                            </li>
                            <li class="nav-item">
                                <RouterLink class="nav-link active" to="/polo">Polos</RouterLink>
                            </li>
                            <li class="nav-item">
                                <RouterLink class="nav-link active" to="/estampado">Estampados</RouterLink>
                            </li>
                            <li class="nav-item">
                                <RouterLink class="nav-link active" to="/camiseta">Camisetas</RouterLink>
                            </li>
                            <li class="nav-item">
                                <RouterLink class="nav-link active" to="/tank">Tanks</RouterLink>
                            </li>
                            <li class="nav-item">
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal"
                                    data-bs-target="#modalingresar">Ingresar</button>
                                <div class="modal fade" id="modalingresar" tabindex="-1" aria-hidden="true"
                                    aria-labelledby="modaltitle">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="modaltitle">Iniciar sesión</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mx-5" role="form" name="frm_login" enctype="multipart/form-"
                                                    autocomplete="off">
                                                    <div class="form-group mb-3">
                                                        <input v-model="correo" type="email" class="form-control"
                                                            id="correo" placeholder="Email">
                                                    </div>
                                                    <div class="form-group mb-4">
                                                        <input v-model="contraseña" type="password" class="form-control"
                                                            id="contraseña" placeholder="Contraseña">
                                                    </div>
                                                    <div class="d-grid gap-2 col-6 mx-auto mb-5">
                                                        <button data-bs-dismiss="modal" class="btn btn-dark"
                                                            @click="solicitarToken">Iniciar Sesion
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="nav-item">
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal"
                                    data-bs-target="#modalregistrar">Registrarse</button>
                                <div class="row">
                                    <Registro />
                                </div>
                            </li>
                            <li class="nav-item">
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal"
                                    data-bs-target="#modalcarrito" @click="llenarCarrito">Carrito</button>
                                <div class="modal fade" id="modalcarrito" tabindex="-1" aria-hidden="true"
                                    aria-labelledby="modaltitle">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h1 class="mb-3 text-center">Carrito de compras</h1>
                                                <table class="table table-secondary table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Producto</th>
                                                            <th>Precio</th>
                                                            <th>Talla</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr v-for="producto in this.productosCarrito">
                                                            <td>{{ producto.descripcion }}</td>
                                                            <td>{{ producto.precio }}</td>
                                                            <td>{{ producto.talla }}</td>
                                                            <td><button data-bs-dismiss="modal" class="btn btn-dark"
                                                                    >eliminar
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="d-grid gap-2 col-6 mx-auto mb-5">
                                                    <button data-bs-dismiss="modal" class="btn btn-dark"
                                                        @click="pagarCarrito">Pagar
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <form class="col-3 d-flex px-5" role="search">
                        <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Buscar</button>
                    </form>
                </div>
            </nav>
        </div>
        <div>
            <RouterView />
        </div>
        <div class="row">
            <Pie />
        </div>
    </div>
</template>
<script>
import Home from './components/Home.vue';
import Pie from './components/Pie.vue';
import Registro from './components/Registro.vue';

import { RouterLink, RouterView } from 'vue-router';

export default {
    data() {
        return {
            nombreTienda: "TiendaRopa",
            urlToken: "http://150.136.93.126:8080/tiendaropa/api/token",
            urlCarrito: "http://150.136.93.126:8080/tiendaropa/api/carrito/"+sessionStorage.getItem('correo'),
            nombre: '',
            correo: '',
            contraseña: '',
            carrito: [],
            productosCarrito: []
        };
    },
    methods: {
        llenarCarrito() {
            const opciones = {
                method: 'GET',
                headers: {
                    'Content-Type': "application/json",
                    'Authorization': "Bearer "+sessionStorage.getItem('token')
                }
            };
            fetch(this.urlCarrito, opciones).then(async (response) => {
                if (response.ok) {
                    this.carrito = await response.json();
                    this.productosCarrito = this.carrito.productos;
                    console.log("producto del carrito: "+this.carrito.productos[1].descripcion);
                }
                else {
                    const error = new Error(response.statusText);
                    error.json = response.json();
                    console.log(error.message);
                    throw error;
                }
            })
        },
        solicitarToken() {
            const opciones = {
                method: 'POST',
                headers: {
                    'Content-Type': "application/json",
                    'Cache-Control': 'no-cache'
                },
                body: JSON.stringify({
                    correo: this.correo,
                    contrasena: this.contraseña
                })
            };
            fetch(this.urlToken, opciones).then(async (response) => {
                if (response.ok) {
                    const respuesta = await response.json();
                    this.token = respuesta.token;
                    sessionStorage.setItem('token', this.token);
                    sessionStorage.setItem('correo', this.correo);
                    this.limpiarFormulario();
                } else {
                    alert("Datos errados")
                }
            })
        },
        limpiarFormulario() {
            this.nombre = '';
            this.correo = '';
            this.contraseña = '';
        }
    },
    components: { Pie, Home, Registro }
}
</script>
<style scoped>
.nombreTienda {
    font-size: 1.5em;
    font-weight: bold;
    padding: 15px;
    margin: 0;
}
</style>