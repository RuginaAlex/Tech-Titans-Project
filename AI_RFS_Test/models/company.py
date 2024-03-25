class Company:
    def __init__(self, id, name, grade, ticker, last_date_fetched):
        self.id = id
        self.name = name
        self.grade = grade
        self.ticker = ticker
        self.last_date_fetched = last_date_fetched

    def __str__(self):
        return f'{self.name} ({self.ticker})'

    def __repr__(self):
        return f'{self.name} ({self.ticker})'

    def __eq__(self, other):
        return self.ticker == other.ticker

    def __hash__(self):
        return hash(self.ticker)
