import 'package:flutter/material.dart';
import 'package:flutter_app/pages/top_page.dart';
import 'package:flutter_app/pages/trading_page.dart';
import 'package:flutter_app/utils/navbar.dart';

import '../models/user.dart';
import 'account_page.dart';
import 'budget_page.dart';

class MainPage extends StatefulWidget {
  final User user;

  const MainPage({super.key, required this.user});

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  int _currentIndex = 1;

  late List<Widget> _pages;

  @override
  void initState() {
    super.initState();
    _pages = [
      TradingPage(user: widget.user),
      TopPage(user: widget.user),
      BudgetPage(user: widget.user),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const PreferredSize(
        preferredSize: Size.fromHeight(60.0),
        child: Navbar(),
      ),
      backgroundColor: Colors.white,
      body: LayoutBuilder(
        builder: (BuildContext context, BoxConstraints viewportConstraints) {
          return SingleChildScrollView(
            child: ConstrainedBox(
              constraints: BoxConstraints(
                minHeight: viewportConstraints.maxHeight,
              ),
              child: _pages[_currentIndex],
            ),
          );
        },
      ),
      bottomNavigationBar: BottomNavigationBar(
        backgroundColor: Colors.amber,
        fixedColor: Colors.black87,
        currentIndex: _currentIndex,
        onTap: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
        items: const [
          BottomNavigationBarItem(
              icon: Icon(Icons.attach_money),
              label: 'Trading',
              backgroundColor: Colors.amber),
          BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'Home',
              backgroundColor: Colors.amber),
          BottomNavigationBarItem(
              icon: Icon(Icons.money_off),
              label: 'Budget',
              backgroundColor: Colors.amber),
        ],
      ),
    );
  }
}