import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CountersComponent } from './counters/counters.component';
import { CounterDetailComponent } from './counter-detail/counter-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/counters', pathMatch: 'full' },
  { path: 'counters', component: CountersComponent },
  { path: 'detail/:id', component: CounterDetailComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
