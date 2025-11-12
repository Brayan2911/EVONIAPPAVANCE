package com.app.evoniapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime
import androidx.annotation.RequiresApi
import android.os.Build

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onFinished()
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Si quieres usar imagen, agrega evoni_logo a res/drawable y cambia el Text por Image(...)
        Text("EVONI", style = MaterialTheme.typography.headlineLarge)
    }
}


@Composable
fun LoginScreen(onLogin: () -> Unit, onRegister: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenido", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo o número de celular") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLogin, modifier = Modifier.fillMaxWidth()) {
            Text("Iniciar sesión")
        }
        TextButton(onClick = onRegister, modifier = Modifier.align(Alignment.End)) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}


@Composable
fun RegisterScreen(onRegistered: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Registro", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(email, { email = it }, label = { Text("Correo o número de celular") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(pass, { pass = it }, label = { Text("Contraseña (mín 8)") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(pass2, { pass2 = it }, label = { Text("Confirmar contraseña") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(name, { name = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(username, { username = it }, label = { Text("Usuario") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onRegistered,
            enabled = pass.length >= 8 && pass == pass2,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Crear cuenta") }
    }
}

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun HomeScreen(
    onOpenTasks: () -> Unit,
    onOpenGuests: () -> Unit,
    onOpenBudget: () -> Unit,
    onOpenEvents: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenProfile: () -> Unit,
) {
    val target = remember { LocalDateTime.now().plusDays(1) }
    var remaining by remember { mutableStateOf(Duration.between(LocalDateTime.now(), target)) }

    LaunchedEffect(target) {
        while (true) {
            remaining = Duration.between(LocalDateTime.now(), target)
            delay(1000)
        }
    }

    val totalSeconds = remaining.seconds
    val days = totalSeconds / (24 * 3600)
    val hours = (totalSeconds % (24 * 3600)) / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Inicio", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(8.dp))
        Text("$days d : $hours h : $minutes m : $seconds s")
        Spacer(Modifier.height(16.dp))
        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onOpenTasks, modifier = Modifier.weight(1f)) { Text("Tareas") }
                Button(onClick = onOpenGuests, modifier = Modifier.weight(1f)) { Text("Invitados") }
            }
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onOpenBudget, modifier = Modifier.weight(1f)) { Text("Presupuesto") }
                Button(onClick = onOpenEvents, modifier = Modifier.weight(1f)) { Text("Evento") }
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = onOpenProfile, modifier = Modifier.fillMaxWidth()) { Text("Perfil") }
        }
    }
}


