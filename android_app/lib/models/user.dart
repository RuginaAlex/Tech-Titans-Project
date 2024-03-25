class User {
  int id;
  String username;
  String password;

  User(this.id, this.username, this.password);

  static fromJson(jsonData) {
    return User(
        jsonData['user_id'], jsonData['username'], jsonData['password_hash']);
  }

  @override
  String toString() {
    return 'User{id: $id, username: $username, password: $password}';
  }
}
