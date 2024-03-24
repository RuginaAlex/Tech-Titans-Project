import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/diagnostics.dart';

import '../models/user.dart';
import '../pages/trading_page.dart';

class CompanySquare extends StatelessWidget {
  final String name;
  final String logo;
  final double value;
  final String ticker;
  final User user;

  const CompanySquare(this.ticker, this.name, this.logo, this.value,
      {super.key, required this.user});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const SizedBox(
          height: 10,
        ),
        Image(
          image: AssetImage(logo),
          height: 100,
        ),
        const SizedBox(
          height: 10,
        ),
        Text(
          "$name ($ticker)",
          style: const TextStyle(
            color: Color.fromRGBO(0, 0, 0, 0.5764705882352941),
            fontSize: 20,
            fontWeight: FontWeight.normal,
          ),
        ),
        const SizedBox(
          height: 10,
        ),
        ElevatedButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => TradingPage(user: user),
              ),
            );
          },
          child: const Text("Trade"),
        ),
      ],
    );
  }
}
