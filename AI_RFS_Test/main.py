import numpy as np
import pandas as pd
import sklearn
import sklearn.preprocessing
import matplotlib.pyplot as plt
import tensorflow as tf

# import dataset
dataset = pd.read_csv('dataset/TMO.csv', index_col=0)
df_stock = dataset.copy()
df_stock = df_stock.dropna()
df_stock = df_stock[['Open', 'High', 'Low', 'Close']]


# data scaling (normalizing)
def normalize_data(df):
    min_max_scaler = sklearn.preprocessing.MinMaxScaler()
    df_scaled = min_max_scaler.fit_transform(df)
    return df_scaled


df_stock_norm = normalize_data(df_stock)

# Splitting the dataset into Train, Valid & test data
valid_set_size_percentage = 10
test_set_size_percentage = 10
seq_len = 20  # taken sequence length as 20


def load_data(stock, seq_len):
    data_raw = stock
    data = []
    for index in range(len(data_raw) - seq_len):
        data.append(data_raw[index: index + seq_len])
    data = np.array(data)
    valid_set_size = int(np.round(valid_set_size_percentage / 100 * data.shape[0]))
    test_set_size = int(np.round(test_set_size_percentage / 100 * data.shape[0]))
    train_set_size = data.shape[0] - (valid_set_size + test_set_size)
    x_train = data[:train_set_size, :-1, :]
    y_train = data[:train_set_size, -1, :]
    x_valid = data[train_set_size:train_set_size + valid_set_size, :-1, :]
    y_valid = data[train_set_size:train_set_size + valid_set_size, -1, :]
    x_test = data[train_set_size + valid_set_size:, :-1, :]
    y_test = data[train_set_size + valid_set_size:, -1, :]
    return [x_train, y_train, x_valid, y_valid, x_test, y_test]


x_train, y_train, x_valid, y_valid, x_test, y_test = load_data(df_stock_norm, seq_len)

# Building the Model
n_steps = seq_len - 1
n_inputs = 4
n_neurons = 200
n_outputs = 4
n_layers = 2
learning_rate = 0.001
batch_size = 50
n_epochs = 100
train_set_size = x_train.shape[0]
test_set_size = x_test.shape[0]

tf.keras.backend.clear_session()

model = tf.keras.Sequential([
    tf.keras.layers.SimpleRNN(units=n_neurons, activation='elu', return_sequences=True,
                              input_shape=[n_steps, n_inputs]),
    tf.keras.layers.SimpleRNN(units=n_neurons, activation='elu', return_sequences=True),
    tf.keras.layers.SimpleRNN(units=n_neurons, activation='elu', return_sequences=True),
    tf.keras.layers.SimpleRNN(units=n_neurons, activation='elu'),
    tf.keras.layers.Dense(n_outputs)
])

model.compile(loss='mse', optimizer='adam')

# Fitting the model
history = model.fit(x_train, y_train, epochs=n_epochs, batch_size=batch_size, validation_data=(x_valid, y_valid))

# Predictions
y_test_pred = model.predict(x_test)

# Plotting the graph
plt.figure(figsize=(10, 5))
plt.plot(y_test[:, 3], color='blue', label='Target')
plt.plot(y_test_pred[:, 3], color='black', label='Prediction')
plt.legend()
plt.show()
