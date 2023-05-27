function encriptar(texto) {
    texto = texto.toLowerCase();
    var textoEncriptado = "";
    var keyA = "ai";
    var keyE = "enter";
    var keyI = "imes";
    var keyO = "ober";
    var keyU = "ufat";

    for (var i = 0; i < texto.length; i++) {
        if(texto.charAt(i) == "a"){
            textoEncriptado += keyA;
        } else if(texto.charAt(i) == "e"){
            textoEncriptado += keyE;
        } else if(texto.charAt(i) == "i"){
            textoEncriptado += keyI;
        }else if(texto.charAt(i) == "o"){
            textoEncriptado += keyO;
        }else if(texto.charAt(i) == "u"){
            textoEncriptado += keyU;
        }else{
            textoEncriptado += texto.charAt(i);
        }
    }
    console.log(textoEncriptado);
    return textoEncriptado;
}


function desEncriptar(texto){
    texto = texto.toLowerCase();
    var textDesEncriptado = "";
    for(var i = 0; i < texto.length;i++){
        if(texto.charAt(i) == "a"){
            textDesEncriptado += texto.charAt(i);
            i += 1;
        } else if(texto.charAt(i) == "e"){
            textDesEncriptado += texto.charAt(i);
            i += 4;
        } else if(texto.charAt(i) == "i"){
            textDesEncriptado += texto.charAt(i);
            i += 3;
        }else if(texto.charAt(i) == "o"){
            textDesEncriptado += texto.charAt(i);
            i += 3;
        }else if(texto.charAt(i) == "u"){
            textDesEncriptado += texto.charAt(i);
            i += 3;
        }else{
            textDesEncriptado += texto.charAt(i);
        }
    }
    console.log(textDesEncriptado);
    return textDesEncriptado;
}
