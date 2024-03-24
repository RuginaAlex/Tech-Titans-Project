import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/pages/trading_page.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:http/http.dart' as http;

import '../models/user.dart';
import '../utils/company_square.dart';
import '../utils/config.dart';

class TopPage extends StatelessWidget {
  const TopPage({super.key, required this.user});

  final User user;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const SizedBox(
            height: 50,
          ),
          Center(
              child: Column(
            children: [
              const SizedBox(
                height: 55,
              ),
              const SizedBox(
                height: 20,
                child: Text(
                  "Current Account USD",
                  textDirection: TextDirection.ltr,
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: Color.fromRGBO(0, 0, 0, 0.5764705882352941),
                    fontSize: 10,
                    fontWeight: FontWeight.normal,
                  ),
                ),
              ),
              FutureBuilder<String>(
                future: get_budget(user.id),
                builder:
                    (BuildContext context, AsyncSnapshot<String> snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const CircularProgressIndicator();
                  } else {
                    if (snapshot.hasError) {
                      return Text('Error: ${snapshot.error}');
                    } else {
                      return Text(
                        '${snapshot.data}',
                        style: const TextStyle(
                          fontSize: 46,
                          fontWeight: FontWeight.w700,
                        ),
                      );
                    }
                  }
                },
              ),
            ],
          )),
          const SizedBox(
            height: 80,
          ),
          GridView.count(
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            crossAxisCount: 2,
            mainAxisSpacing: 20,
            children: [
              CompanySquare('AAPL', 'Apple Inc.', 'lib/assets/images/coin.jpg', 145.32,
                  user: user),
              CompanySquare('AAPL', 'Apple Inc.', 'lib/assets/images/coin.jpg', 145.32,
                  user: user),
              const Column(
                children: [
                  SizedBox(
                    height: 10,
                  ),
                  Icon(
                    Icons.monetization_on_rounded,
                    color: Color.fromRGBO(255, 213, 0, 1.0),
                    size: 100,
                  ),
                ],
              ),
              const Column(
                children: [
                  SizedBox(
                    height: 10,
                  ),
                  Icon(
                    Icons.monetization_on_rounded,
                    color: Color.fromRGBO(255, 213, 0, 1.0),
                    size: 100,
                  ),
                ],
              ),
            ],
          ),
        ],
      ),
    );
  }
}

Future<String> get_budget(id) async {
  var url = Uri.parse('http://${Config.ip}:8080/api/bankAccount/$id');
  var userData = await http.get(url);
  if (userData.statusCode == 200) {
    var jsonData = json.decode(userData.body);
    return "${jsonData['balance']}";
  }
  return "0.00";
}
