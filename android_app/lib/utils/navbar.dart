import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class Navbar extends StatelessWidget {
  const Navbar({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.black,
        body: SingleChildScrollView( // Add this
          child: Column(
            children: [
              const SizedBox(
                height: 45,
              ),
              Container(
                color: Colors.black,
                child: Center(
                  child: Text(
                    "SmartBudget",
                    style: GoogleFonts.robotoSlab(
                        color: const Color.fromRGBO(255, 213, 0, 1.0),
                        fontSize: 40,
                        fontWeight: FontWeight.w500
                    ),
                  ),
                ),
              ),
            ],
          ),
        ));
  }
}
