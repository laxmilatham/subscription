import React from 'react';
import { Provider } from 'react-redux';
import SubscriptionForm from './components/SubscriptionForm';
import store from './redux/store';

const App = () => {
  return (
    <Provider store={store}>
      <div className="App">
        <h1>Subscription Form</h1>
        <SubscriptionForm />
      </div>
    </Provider>
  );
};

export default App;
