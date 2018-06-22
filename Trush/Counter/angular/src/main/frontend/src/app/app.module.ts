import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { CounterService } from './counter.service';
import { CountersComponent } from './counters/counters.component';
import { AppRoutingModule } from './app-routing.module';
import { CounterDetailComponent } from './counter-detail/counter-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    CountersComponent,
    CounterDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [CounterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
