import 'package:flutter/material.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({super.key});

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('12:30'),
        backgroundColor: Colors.grey[300],
      ),
      body: Column(
        children: <Widget>[
          Expanded(
            child: Container(
              color: Colors.grey[300],
            ),
          ),
          Container(
            padding: const EdgeInsets.all(20),
            color: Colors.white,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: <Widget>[
                const Text(
                  'Login or sign up',
                  style: TextStyle(fontSize: 24),
                ),
                const SizedBox(height: 10),
                const Text(
                  'Please select your preferred method to continue setting up your account',
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {},
                  child: const Text('Continue with Email'),
                ),
                const SizedBox(height: 10),
                ElevatedButton(
                  onPressed: () {},
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.white),
                    foregroundColor: MaterialStateProperty.all(Colors.black),
                  ),
                  child: const Row(
                    children: <Widget>[
                      Icon(Icons.mail),
                      Text('G'),
                    ],
                  ),
                ),
                const SizedBox(height: 10),
                ElevatedButton(
                  onPressed: () {},
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.blue),
                  ),
                  child: const Row(
                    children: <Widget>[
                      Icon(Icons.face),
                      Text('f'),
                    ],
                  ),
                ),
              ],
            ),
          ),
          Container(
            padding: const EdgeInsets.only(top: 20, bottom: 20, left: 40, right: 40),
            color: Colors.white,
            child: const Text(
              "If you are creating a new account,\nTerms & Conditions and Privacy Policy will apply.",
              textAlign: TextAlign.center,
            ),
          ),
        ],
      ),
    );
  }
}
