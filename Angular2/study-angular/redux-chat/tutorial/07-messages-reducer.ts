interface AppState {
    messages: string[];
}

interface Action {
    type: string;
    payload?: any;
}

interface Reducer<T> {
    (state: T, action: Action): T;
}

export interface ListenerCallback {
    (): void;
  }
  
  export interface UnsubscribeCallback {
    (): void;
  }
  
  export class Store<T> {
    private _state: T;
    private _listeners: ListenerCallback[] = [];
  
    constructor(
      private reducer: Reducer<T>,
      initialState: T
    ) {
      this._state = initialState;
    }
  
    getState(): T {
      return this._state;
    }
  
    dispatch(action: Action): void {
      this._state = this.reducer(this._state, action);
      this._listeners.forEach((listener: ListenerCallback) => listener());
    }
  
    subscribe(listener: ListenerCallback): UnsubscribeCallback {
      this._listeners.push(listener);
      return () => { // returns an "unsubscribe" function
        this._listeners = this._listeners.filter(l => l !== listener);
      };
    }
  }  

interface AddMessageAction extends Action {
    message: string;
}

interface DeleteMessageAction extends Action {
    index: number;
}


let reducer: Reducer<AppState> = 
    (state: AppState, action: Action): AppState => {
    switch(action.type) {
        case 'ADD_MESSAGE':
            return {
                messages: state.messages.concat(
                    (<AddMessageAction> action).message
                )//,
            };
        case 'DELETE_MESSAGE':
            let idx = (<DeleteMessageAction> action).index;
            return {
                messages: [
                    ...state.messages.slice(0, idx),
                    ...state.messages.slice(0 + 1, state.messages.length)
                ]
            };
        default:
            return state;
    }
};

let store = new Store<AppState>(reducer, { messages: [] });
console.log(store.getState());


store.dispatch({
    type: 'ADD_MESSAGE',
    message: 'Would you say the fringe was made of silk?'
} as AddMessageAction);

store.dispatch({
    type: 'ADD_MESSAGE',
    message: 'Wouldnt have no other kind but silk'
} as AddMessageAction);

store.dispatch({
    type: 'ADD_MESSAGE',
    message: 'Has it really got a team of snow white horses?'
} as AddMessageAction);

console.log(store.getState());