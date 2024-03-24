import copy
import pickle

from sklearn.preprocessing import MinMaxScaler
import os
import numpy as np
import pandas as pd
import time
import random
from Model import Model
from Agent import Agent
from aux_functions import get_state

import matplotlib.pyplot as plt
import seaborn as sns

sns.set()

if __name__ == '__main__':
    df = pd.read_csv('../dataset/GOOG.csv')
    df.head()
    parameters = [df['Close'].tolist(), df['Volume'].tolist()]
    inventory_size = 1
    mean_inventory = 0.5
    capital = 1000
    last_state = 0
    concat_parameters = np.concatenate([get_state(parameters, 20), [[inventory_size,
                                                                     mean_inventory,
                                                                     capital]]], axis=1)
    skip = 1
    layer_size = 500
    output_size = 3
    window_size = 20

    input_size = concat_parameters.shape[1]
    stocks = [i for i in os.listdir('../dataset') if '.csv' in i and not 'AMD' in i]

    model = Model(input_size=input_size, layer_size=layer_size, output_size=output_size)
    agent = None

    for no, stock in enumerate(stocks):
        print('training stock %s' % stock)
        df = pd.read_csv('../dataset/' + stock)
        real_trend = df['Close'].tolist()
        parameters = [df['Close'].tolist(), df['Volume'].tolist()]
        minmax = MinMaxScaler(feature_range=(100, 200)).fit(np.array(parameters).T)
        scaled_parameters = minmax.transform(np.array(parameters).T).T.tolist()
        initial_money = np.max(parameters[0]) * 2

        if no == 0:
            agent = Agent(model=model,
                          timeseries=scaled_parameters,
                          skip=skip,
                          initial_money=initial_money,
                          real_trend=real_trend,
                          minmax=minmax)
        else:
            agent.change_data(timeseries=scaled_parameters,
                              skip=skip,
                              initial_money=initial_money,
                              real_trend=real_trend,
                              minmax=minmax)

        agent.fit(iterations=100, checkpoint=10)
        print()

    df = pd.read_csv('../dataset/AMD.csv')
    real_trend = df['Close'].tolist()
    parameters = [df['Close'].tolist(), df['Volume'].tolist()]
    minmax = MinMaxScaler(feature_range=(100, 200)).fit(np.array(parameters).T)
    scaled_parameters = minmax.transform(np.array(parameters).T).T.tolist()
    initial_money = np.max(parameters[0]) * 2

    agent.change_data(timeseries=scaled_parameters,
                      skip=skip,
                      initial_money=initial_money,
                      real_trend=real_trend,
                      minmax=minmax)

    states_buy, states_sell, total_gains, invest = agent.buy()

    volume = df['Volume'].tolist()

    for i in range(100):
        print(agent.trade([real_trend[i], volume[i]]))

    fig = plt.figure(figsize=(15, 5))
    plt.plot(df['Close'], color='r', lw=2.)
    plt.plot(df['Close'], '^', markersize=10, color='m', label='buying signal', markevery=states_buy)
    plt.plot(df['Close'], 'v', markersize=10, color='k', label='selling signal', markevery=states_sell)
    plt.title('AMD total gains %f, total investment %f%%' % (total_gains, invest))
    plt.legend()
    plt.show()

    copy_model = copy.deepcopy(agent.model)

    with open('model.pkl', 'wb') as fopen:
        pickle.dump(copy_model, fopen)
