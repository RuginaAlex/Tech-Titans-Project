class CompanyData:
    def __init__(self, id, company, open, high, low, close, date):
        self.id = id
        self.company = company
        self.open = open
        self.high = high
        self.low = low
        self.close = close
        self.date = date

    def __str__(self):
        return f'{self.company} on {self.date} with open {self.open} and close {self.close}'

    def __repr__(self):
        return f'{self.company} on {self.date}'

    def __eq__(self, other):
        return self.company == other.company and self.date == other.date

    def __hash__(self):
        return hash((self.company, self.date))
