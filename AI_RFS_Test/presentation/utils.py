import os
from functools import reduce

import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

sns.set()
directory = 'dataset/'
files = os.listdir(directory)
stocks = [directory + s for s in files if s.endswith('.csv')]

# Read each file and store the number of rows in a list
num_entries = [pd.read_csv(s).shape[0] for s in stocks]

# Read 'Date' and 'Close' columns from each file
dfs = [pd.read_csv(s)[['Date', 'Close']] for s in stocks]

# Calculate returns and volatilities for each company and store them in a list of DataFrames
combine_dfs = []
for i in range(len(dfs)):
    data = dfs[i].iloc[:, 1:]
    returns = data.pct_change()
    mean_daily_returns = returns.mean()
    volatilities = returns.std()

    # Multiply mean_daily_returns and volatilities by num_entries for the current company
    combine_df = pd.DataFrame({'returns': mean_daily_returns * num_entries[i],
                               'volatility': volatilities * num_entries[i]})
    combine_dfs.append(combine_df)

combine = pd.concat(combine_dfs)
# Concatenate the list of DataFrame
g = sns.jointplot(x="volatility", y="returns", data=combine, kind="reg", height=10)
for i in range(combine.shape[0]):
    plt.annotate(files[i].replace('.csv', ''), (combine.iloc[i, 1], combine.iloc[i, 0]))

plt.show()
