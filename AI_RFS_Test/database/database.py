import requests as requests


def get_all_company_data():
    url = "http://localhost:8080/api/companiesData"
    response = requests.get(url)

    if response.status_code == 200:
        print(response.json())
        return response.json()
    else:
        return None
