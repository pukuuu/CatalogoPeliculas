package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            //si existe no se vuelve a crear
            if (archivo.exists()){
                System.out.println("El archivo ya existe!");
            }
            else{
                //si no existe se crea vacio
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se a creado el archivo");
            }
            //abrir archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            //leer linea a linea el archivo
            String linea;
            linea = entrada.readLine();
            //leemos todas las lineas disponibles
            while(linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                //antes de terminar el ciclo hay que volver a leer la siguiente linea
                linea = entrada.readLine();
            }
            //cerrar el archivo
            entrada.close();
        }
        catch (Exception e){
            System.out.println("Ocurrio un error al abrir el archivo :"+e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {

    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            // Agregamos la pelicula toString
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se agrego al archivo la pelicula: "+pelicula);
        }
        catch (Exception e){
            System.out.println("Ocurrio un error al agregar peliculas: "+e.getMessage());
        }

    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //se abre el archivo para lectura linea a linea
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada= false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null){
                //buscamos sin importar mayusculas o minusculas
                if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;
                }
                                lineaTexto = entrada.readLine();
                indice++;
            }//fin del while
            if(encontrada){
                System.out.println("Pelicula " + lineaTexto + "encontrada - linea: " + indice);
            }
            else{
                System.out.println("No se encontró la pelicula: " + pelicula.getNombre());
            }
            entrada.close();
        }
        catch (Exception e){
            System.out.println("Ocurrio un error al buscar en el archivo: " + e.getMessage());
        }
    }
}
