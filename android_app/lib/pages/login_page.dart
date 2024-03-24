import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/utils/config.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:http/http.dart' as http;

import '../models/user.dart';
import '../utils/large_button.dart';
import '../utils/text_field.dart';
import '../utils/config.dart';
import '../pages/main_page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  FocusNode usernameFocusNode = FocusNode();
  FocusNode passwordFocusNode = FocusNode();

  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width; // Get screen width

    return Scaffold(
      backgroundColor: const Color.fromRGBO(255, 255, 255, 1),
      body: Column(
        children: [
          const Flexible(
            flex: 20,
            child: SingleChildScrollView(
              // Add this
              child: SizedBox(
                height: 100,
              ),
            ),
          ), // Part 1
          Flexible(
            flex: 33,
            child: SingleChildScrollView(
              // Add this
              child: SizedBox(
                width: screenWidth, // Set container width to screen width
                child: Column(
                  children: [
                    const SizedBox(
                      height: 40,
                    ),
                    const Icon(
                      Icons.monetization_on_rounded,
                      color: Color.fromRGBO(255, 213, 0, 1.0),
                      size: 140,
                    ),
                    Center(
                      child: Text("SmartBudget",
                          textDirection: TextDirection.ltr,
                          style: GoogleFonts.inter(
                              fontSize: 45, fontWeight: FontWeight.w700)),
                    ),
                    const SizedBox(
                      height: 90,
                    )
                  ],
                ),
              ),
            ),
          ), // Part 2
          Flexible(
            flex: 50,
            child: SingleChildScrollView(
              // Add this
              child: SizedBox(
                width: screenWidth, // Set container width to screen width
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 40.0),
                      // Add horizontal padding
                      child: CustomTextField(
                        controller: usernameController,
                        hintText: 'Username',
                        iconData: Icons.person,
                        iconColor: const Color.fromRGBO(255, 213, 0, 1.0),
                        iconSize: 30.0,
                        focusNode: usernameFocusNode, // Add this
                      ),
                    ),
                    const SizedBox(height: 35),
                    // Add some spacing between the text fields
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 40.0),
                      // Add horizontal padding
                      child: CustomTextField(
                        controller: passwordController,
                        hintText: 'Password',
                        iconData: Icons.lock,
                        obscureText: true,
                        iconColor: const Color.fromRGBO(255, 213, 0, 1.0),
                        iconSize: 30.0,
                        focusNode: passwordFocusNode, // Add this
                      ),
                    ),
                    const SizedBox(
                      height: 90,
                    ),
                    LargeButton(
                      text: 'Login',
                      backgroundColor: Colors.yellow,
                      textColor: Colors.black,
                      borderRadius: 40,
                      width: 320,
                      onPressed: () {
                        String username = usernameController.text;
                        String password = passwordController.text;
                        var userData = getUserData(username);
                        userData.then((value) {
                          if (value.statusCode == 200) {
                            var jsonData = jsonDecode(value.body);
                            if (jsonData['password_hash'] == password) {
                              print(jsonData);
                              var user = User.fromJson(jsonData);
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => MainPage(user: user),
                                ),
                              );
                            } else {
                              print('Incorrect password');
                            }
                          } else {
                            print('User not found');
                          }
                        });
                      },
                    ),
                    const SizedBox(height: 40),
                    // Add some spacing between the buttons
                    LargeButton(
                      text: 'Sign Up',
                      backgroundColor: Colors.black,
                      textColor: Colors.yellow,
                      borderRadius: 40,
                      width: 210,
                      onPressed: () {
                        Navigator.pushNamed(context, '/signup');
                      },
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

Future<http.Response> getUserData(String username) async {
  var url =
      Uri.parse('http://${Config.ip}:8080/api/user/username/$username');
  return await http.get(url);
}
