const initialState = {
    name: "",
    email: "",
    userType: "",
    company: "",
    appType: "",
  };
  
  const subscriptionReducer = (state = initialState, action) => {
    switch (action.type) {
      case "SUBMIT_FORM":
        return {
          ...state,
          name: action.payload.name,
          email: action.payload.email,
          userType: action.payload.userType,
          company: action.payload.company,
          appType: action.payload.appType,
        };
      default:
        return state;
    }
  };
  
  export default subscriptionReducer;
  