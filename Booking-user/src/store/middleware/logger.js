const logging =  store => next => action => {
  console.log(`type: ${action.type}`, action);
  const result = next(action);
  console.log('next state', store.getState());
  return result;
};

export default logging;
  