package com.dam;

import com.dam.User;
import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.json.JavalinGson;
import io.javalin.http.Context;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class Main2 {
    // Instancia de GSON para manejar JSON
    private static final Gson gson = new Gson();
    // Un generador de IDs falsos para la simulación
    private static final Random random = new Random();

    public static void main(String[] args) {
//        Javalin app = Javalin.create().start(7001);
        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinGson());
        }).start(7001);
        System.out.println("Servidor API (Mock) iniciado en http://localhost:7001");
        // Definición de Endpoints
        app.post("/users", com.dam.Main2::crearUsuario);
        app.get("/users", com.dam.Main2::obtenerTodosLosUsuarios);
        app.get("/users/{id}", com.dam.Main2::obtenerUsuarioPorId);
        app.put("/users/{id}", com.dam.Main2::actualizarUsuario);
        app.delete("/users/{id}", com.dam.Main2::eliminarUsuario);

        app.get("/version", ctx -> ctx.json(Map.of("version", "Soy el servidor 2")));
    }
    // --- Implementación de los Handlers (Controladores) ---
    /**
     * CREATE (Mock)
     * Simula la creación de un usuario.
     * Lee el JSON del body, lo convierte a User, y devuelve un mensaje de éxito.
     */
    private static void crearUsuario(Context ctx) {
        // 1. Deserializar el JSON del body a un objeto User
        User newUser = gson.fromJson(ctx.body(), User.class);
        // 2. Simulación de Lógica:
        // En un caso real, aquí iría la lógica de BD (INSERT).
        int simulatedId = random.nextInt(1000) + 1; // Genera un ID falso
        // 3. Respuesta al cliente
        ctx.status(201); // 201 = Created
        ctx.json(Map.of(
                "status", "creado (simulación)",
                "usuario", newUser.name,
                "idAsignado", simulatedId
        ));
    }
    /**
     * READ ALL (Mock)
     * Simula la devolución de una lista de usuarios.
     */
    private static void obtenerTodosLosUsuarios(Context ctx) {
        // 1. Simulación de Lógica:
        // En un caso real, aquí iría la lógica de BD (SELECT *)
        List<User> mockUsers = List.of(
                new User(101, "Ana (Mock)", "ana.mock@example.com"),
                new User(102, "Luis (Mock)", "luis.mock@example.com")
        );
        // 2. Respuesta al cliente
        ctx.status(200); // 200 = OK
        ctx.json(mockUsers);
    }
    /**
     * READ ONE (Mock)
     * Simula la búsqueda de un usuario por su ID.
     */
    private static void obtenerUsuarioPorId(Context ctx) {
        // 1. Obtener el parámetro de la URL
        String id = ctx.pathParam("id");
        // 2. Simulación de Lógica:
        // En un caso real, aquí iría (SELECT ... WHERE id = ?)
        User mockUser = new User(
                Integer.parseInt(id), // Usa el ID de la URL
                "Usuario Simulado " + id,
                "user." + id + "@example.com"
        );
        // 3. Respuesta al cliente
        ctx.status(200);
        ctx.json(mockUser);
    }
    /**
     * UPDATE (Mock)
     * Simula la actualización de un usuario.
     */
    private static void actualizarUsuario(Context ctx) {
        // 1. Obtener el parámetro de la URL
        String id = ctx.pathParam("id");
        // 2. Deserializar el JSON del body
        User updatedUser = gson.fromJson(ctx.body(), User.class);
        // 3. Simulación de Lógica:
        // En un caso real, aquí iría (UPDATE ... WHERE id = ?)
        // 4. Respuesta al cliente
        ctx.status(200);
        ctx.json(Map.of(
                "status", "actualizado (simulación)",
                "id", id,
                "nuevoNombre", updatedUser.name
        ));
    }
    /**
     * DELETE (Mock)
     * Simula la eliminación de un usuario.
     */
    private static void eliminarUsuario(Context ctx) {
        // 1. Obtener el parámetro de la URL
        String id = ctx.pathParam("id");
        // 2. Simulación de Lógica:
        // En un caso real, aquí iría (DELETE ... WHERE id = ?)
        // 3. Respuesta al cliente
        ctx.status(200); // También es común 204 (No Content)
        ctx.json(Map.of(
                "status", "eliminado (simulación)",
                "id", id
        ));
    }
}
