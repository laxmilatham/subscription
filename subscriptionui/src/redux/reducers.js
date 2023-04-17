import { ADD_SUBSCRIPTION } from './actions';

const initialState = {
  subscriptions: [],
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_SUBSCRIPTION:
      return {
        ...state,
        subscriptions: [...state.subscriptions, action.payload],
      };
    default:
      return state;
  }
};

export default reducer;
