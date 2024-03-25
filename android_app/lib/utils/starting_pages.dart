import 'package:flutter/material.dart';

import 'large_button.dart';

class StartingPages extends StatelessWidget {
  final String title;
  final String text;
  final int number;
  final int currentPage;
  final int totalPages;
  final PageController controller;

  const StartingPages({
    super.key,
    required this.title,
    required this.text,
    required this.number,
    required this.currentPage,
    required this.totalPages,
    required this.controller,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: currentPage != 0
            ? IconButton(
                icon: const Icon(Icons.arrow_back, size: 30),
                onPressed: () {
                  controller.animateToPage(
                    currentPage - 1,
                    duration: const Duration(milliseconds: 300),
                    curve: Curves.easeInOut,
                  );
                },
              )
            : null,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              title,
              style: const TextStyle(
                fontSize: 24.0,
                fontWeight: FontWeight.bold,
                color: Colors.black,
              ),
            ),
            Text(
              text,
              style: TextStyle(
                fontSize: 16.0,
                color: Colors.grey[700],
              ),
            ),
            const SizedBox(
              height: 80,
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: LargeButton(
                text: 'Get Started',
                backgroundColor: const Color.fromRGBO(255, 213, 0, 1),
                textColor: Colors.black,
                borderRadius: 20.0,
                width: 350,
                onPressed: () {
                  if (currentPage < totalPages - 1) {
                    // Go to the next page
                    controller.nextPage(
                      duration: const Duration(milliseconds: 300),
                      curve: Curves.easeInOut,
                    );
                  } else {
                    // Go to the SignUpPage
                    Navigator.pushNamed(context, '/signup');
                  }
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
