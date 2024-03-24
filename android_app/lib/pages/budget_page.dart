import 'package:flutter/material.dart';

class BudgetPage extends StatelessWidget {
  const BudgetPage({super.key, required user});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const SizedBox(
            height: 50,
          ),
          const Center(
            child: Icon(Icons.monetization_on_rounded,
                color: Color.fromRGBO(255, 213, 0, 1.0), size: 240),
          ),
          const SizedBox(
            height: 50,
          ),
          GridView.count(
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            crossAxisCount: 2,
            children: [
              // Your grid items go here
              // For example:
              Container(color: Colors.lightGreenAccent),
              Container(color: Colors.blue),
              Container(color: Colors.green),
              Container(color: Colors.yellow),
            ],
          ),
        ],
      ),
    );
  }
}
