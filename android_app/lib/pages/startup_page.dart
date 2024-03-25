import 'package:flutter/material.dart';
import 'package:flutter_app/utils/starting_pages.dart';

class StartUpPage extends StatefulWidget{
  const StartUpPage({super.key});

  @override
  State<StartUpPage> createState() => _StartUpPage();

}

class _StartUpPage extends State<StartUpPage>{
  final PageController _controller = PageController(initialPage: 0);
  int _curentPage = 0;

  @override
  void initState(){
    super.initState();
    _controller.addListener(() {
      setState(() {
        _curentPage = _controller.page!.round();
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: _controller,
        children: [
          StartingPages(title: "SmartBudget", text: "Budgeting and trading powered by AI", number: 1, currentPage: _curentPage, totalPages: 3, controller: _controller,),
          StartingPages(title: "SmartBudget", text: "Budgeting", number: 2, currentPage: _curentPage, totalPages: 3, controller: _controller,),
          StartingPages(title: "SmartBudget", text: "AI", number: 3, currentPage: _curentPage, totalPages: 3, controller: _controller,)
        ],
      ),
    );
  }
}