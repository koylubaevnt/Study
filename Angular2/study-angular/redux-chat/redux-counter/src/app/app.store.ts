import { InjectionToken } from '@angular/core';
import { Store, StoreEnhancer, createStore, compose } from 'redux';
import { AppState } from './app.state';
import { counterReducer as reducer } from './counter.reducer';

export const AppStore = new InjectionToken('App.store');

const devtools: StoreEnhancer<AppState> =
    window['devToolsExtension'] ? window['devToolsExtension']() : f => f;

export function crerateAppStore(): Store<AppState> {
    return createStore<AppState>(
        reducer,
        compose(devtools)
    );
}

export const appStoreProviders = [
    { provide: AppStore, useFactory: crerateAppStore }
];