import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CustomTextField extends StatelessWidget {
  final String hintText;
  final IconData iconData; // New property for the icon
  final bool obscureText; // New property for obscuring text
  final Color iconColor; // New property for the icon color

  final double iconSize; // New property for the icon size

  final FocusNode focusNode;

  final TextEditingController controller; // New property for the focus node

  const CustomTextField({
    super.key,
    required this.hintText,
    required this.iconData,
    this.obscureText = false,
    this.iconColor = Colors.black, // Default color is black
    this.iconSize = 24.0, // Default size is 24.0
    required this.focusNode,
    required this.controller
  });

  @override
  Widget build(BuildContext context) {
    return TextField(
      focusNode: focusNode,
      controller: controller,
      obscureText: obscureText,
      decoration: InputDecoration(
        hintText: hintText,
        prefixIcon: Icon(iconData, color: iconColor),
        contentPadding: const EdgeInsets.only(left: 20.0, top: 20, bottom: 20),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(30.0), // Set the border radius
          borderSide: const BorderSide(),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: const BorderSide(
              color: Colors.orange,
              width: 2.7
          ),
          borderRadius: BorderRadius.circular(30),
        ),
      ),
    );
  }
}
