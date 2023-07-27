import java.util.*

fun main() {
    val perfiles: MutableList<PerfilUsuario> = mutableListOf(
        // Repertorio inicial de usuarios

        PerfilUsuario(1, "Juan", "Perez", null, 25, "per21356@uvg.edu.gt",
            "Le gusta el futbol", "Activo"),


        PerfilUsuario(2, "Maria", "Gomez", null, 30, "gom21562@uvg.edu.gt",
            "Le gustan los carros", "Pendiente"),


        PerfilUsuario(3, "Carlos", "Lopez", null, 22, "lop21763@uvg.edu.gt",
            "Le gusta ver peliculas", "Inactivo")
    )

    var running = true
    while (running) {
        println("|--------------------------------------|")
        println("|                 Menú                 |")
        println("|--------------------------------------|")
        println("|      1. Crear perfil                 |")
        println("|      2. Buscar perfil de usuarios    |")
        println("|      3. Eliminar perfil              |")
        println("|      4. Agregar Hobby                |")
        println("|      5. Salir                        |")
        println("|--------------------------------------|")
        print("Ingrese la opción deseada: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                try {
                    val nuevoPerfil = crearPerfil()
                    perfiles.add(nuevoPerfil)
                    println("Perfil creado exitosamente.")
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            2 -> {
                try {
                    buscarPerfil(perfiles)
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            3 -> {
                try {
                    eliminarPerfil(perfiles)
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            4 -> {
                try {
                    agregarHobby(perfiles)
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            5 -> {
                running = false
            }
            else -> {
                println("Opción no válida. Intente nuevamente.")
            }
        }
    }
}

fun crearPerfil(): PerfilUsuario {
    println("Creando perfil:")
    print("ID: ")
    val id = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("ID inválido.")

    print("Nombres: ")
    val nombres = readLine() ?: throw IllegalArgumentException("Nombres no pueden estar vacíos.")

    print("Apellidos: ")
    val apellidos = readLine() ?: throw IllegalArgumentException("Apellidos no pueden estar vacíos.")

    print("URL de la foto de perfil (deje en blanco si no tiene): ")
    val urlPhoto = readLine()

    print("Edad: ")
    val edad = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Edad inválida.")

    print("Correo electrónico: ")
    val correo = readLine() ?: throw IllegalArgumentException("Correo no puede estar vacío.")

    print("Biografía (deje en blanco si no desea ingresar): ")
    val biografia = readLine()

    print("Estado (Activo/Pendiente/Inactivo): ")
    val estado = readLine()?: throw IllegalArgumentException("Estado no puede estar vacío.")
    if (estado !in listOf("Activo", "Pendiente", "Inactivo")) {
        throw IllegalArgumentException("Estado inválido. Debe ser Activo, Pendiente o Inactivo.")
    }

    return PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografia, estado)
}

fun buscarPerfil(perfiles: List<PerfilUsuario>) {
    println("Buscar perfil:")
    print("Ingrese el ID o nombres y/o apellidos a buscar: ")
    val input = readLine()

    val resultados = perfiles.filter {
        it.id.toString() == input || it.nombres.contains(input ?: "", ignoreCase = true) || it.apellidos.contains(input ?: "", ignoreCase = true
        )
    }

    if (resultados.isNotEmpty()) {
        for (perfil in resultados) {
            println("ID: ${perfil.id}")
            println("Nombres: ${perfil.nombres}")
            println("Apellidos: ${perfil.apellidos}")
            println("URL de la foto de perfil: ${perfil.urlPhoto ?: "No disponible"}")
            println("Edad: ${perfil.edad}")
            println("Correo electrónico: ${perfil.correo}")
            println("Biografía: ${perfil.biografia ?: "No disponible"}")
            println("Estado: ${perfil.estado}")
            println("Hobbies:")
            if (perfil.hobbies.isNotEmpty()) {
                for ((index, hobby) in perfil.hobbies.withIndex()) {
                    println("${index + 1}. ${hobby.titulo}")
                    println("   Descripción: ${hobby.descripcion}")
                    println("   URL de la foto: ${hobby.urlPhoto ?: "No disponible"}")
                }
            } else {
                println("   No tiene hobbies.")
            }
            println("--------------")
        }
    } else {
        println("No se encontró ningún perfil con la información ingresada.")
    }
}

fun eliminarPerfil(perfiles: MutableList<PerfilUsuario>) {
    println("Eliminar perfil:")
    print("Ingrese el ID del perfil a eliminar: ")
    val id = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("ID inválido.")

    val perfilEncontrado = perfiles.find { it.id == id }
    if (perfilEncontrado != null) {
        perfiles.remove(perfilEncontrado)
        println("Perfil eliminado exitosamente.")
    } else {
        println("No se encontró ningún perfil con el ID ingresado.")
    }
}

fun agregarHobby(perfiles: MutableList<PerfilUsuario>) {
    println("Agregar Hobby:")
    print("Ingrese el ID o nombres y/o apellidos del perfil al que desea agregar el Hobby: ")
    val input = readLine()

    val perfilEncontrado = perfiles.find {
        it.id.toString() == input || it.nombres.contains(input ?: "", ignoreCase = true) || it.apellidos.contains(input ?: "", ignoreCase = true
        )
    }

    if (perfilEncontrado != null) {
        println("Perfil encontrado:")
        println("ID: ${perfilEncontrado.id}")
        println("Nombres: ${perfilEncontrado.nombres}")
        println("Apellidos: ${perfilEncontrado.apellidos}")
        print("Título del Hobby: ")
        val titulo = readLine() ?: throw IllegalArgumentException("Título del Hobby no puede estar vacío.")

        print("Descripción del Hobby: ")
        val descripcion = readLine() ?: throw IllegalArgumentException("Descripción del Hobby no puede estar vacía.")

        print("URL de la foto del Hobby (deje en blanco si no tiene): ")
        val urlPhoto = readLine()

        val hobby = Hobby(titulo, descripcion, urlPhoto)
        perfilEncontrado.agregarHobby(hobby)

        println("Hobby agregado exitosamente.")
    } else {
        println("No se encontró ningún perfil con la información ingresada.")
    }
}
