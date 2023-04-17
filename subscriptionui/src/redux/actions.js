export const ADD_SUBSCRIPTION = 'ADD_SUBSCRIPTION';

export const addSubscription = (subscription) => {
  return {
    type: ADD_SUBSCRIPTION,
    payload: subscription,
  };
};
