import 'package:flutter/material.dart';
import 'package:flutter_app/models/user.dart';
import 'package:flutter_app/pages/login_page.dart';
import 'package:flutter_app/pages/main_page.dart';
import 'package:flutter_app/pages/signup_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      home: const LoginPage(),
      routes: {
        '/login': (context) => const LoginPage(),
        '/main': (context) => MainPage(user: User(1, 'username', 'password')),
        '/signup': (context) => const SignUpPage(),
      },
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
    );
  }
}
