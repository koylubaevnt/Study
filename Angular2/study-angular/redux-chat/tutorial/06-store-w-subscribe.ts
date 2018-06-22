interface Action {
    type: string;
    payload?: any;
}

interface Reducer<T> {
    (state: T, action: Action): T;
}

interface SubscribeCallback {
    (): void;
}

interface UnsubscribeCallback {
    (): void;
}

class Store<T> {
    private _state: T;
    private _listeners: SubscribeCallback[] = [];

    constructor(
        private reducer: Reducer<T>,
        initialState: T
    ) {
        this._state = initialState;
    }

    subscribe(listener: SubscribeCallback): UnsubscribeCallback {
        this._listeners.push(listener);
        return () => {
            this._listeners = this._listeners.filter(l => l!== listener);
        }
    }

    dispatch(action: Action): void {
        this._state = this.reducer(this._state, action);
        this._listeners.forEach((listener: SubscribeCallback) => listener());
    }

    getState(): T {
        return this._state;
    }
}

let reducer: Reducer<number> = (state: number, action: Action) => {
    switch (action.type) {
    case 'INCREMENT':
      return state + 1;
    case 'DECREMENT':
      return state - 1;
    case 'PLUS':
      return state + action.payload;
    default:
      return state;
    }
  };
  
  // create a new store
  let store = new Store<number>(reducer, 0);
  console.log(store.getState()); // -> 0
  
  // subscribe
  let unsubscribe = store.subscribe(() => {
    console.log('subscribed: ', store.getState());
  });
  
  store.dispatch({ type: 'INCREMENT' }); // -> subscribed: 1
  store.dispatch({ type: 'INCREMENT' }); // -> subscribed: 2
  
  unsubscribe();
  store.dispatch({ type: 'DECREMENT' }); // (nothing logged)
  
  // decrement happened, even though we weren't listening for it
  console.log(store.getState()); // -> 1
  