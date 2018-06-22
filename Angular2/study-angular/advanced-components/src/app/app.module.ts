import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { Router, Routes, RouterModule } from '@angular/router';

import { APP_BASE_HREF, LocationStrategy, HashLocationStrategy } from '@angular/common';

import { ExampleDef } from './example.model';

import { AppComponent } from './app.component';
import { IntroComponent } from './intro/intro.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidebarItemComponent } from './sidebar/sidebar-item.component';


import { StylingModule } from './styling/styling.module';
import { HostModule } from './host/host.module';
import { ContentProjectionModule } from './content-projection/content-projection.module';
import { TabsModule } from './tabs/tabs.module';
import { LifecycleModule } from './lifecycle/lifecycle.module';
import { TemplatesModule } from './templates/templates.module';
import { ChangeDetectionModule } from './change-detection/change-detection.module';

import { StylingDemoComponent } from './styling/styling-demo/styling-demo.component';
import { PopupDemoComponent } from './host/popup-demo/popup-demo.component'; 
import { PopupDemoComponent1 } from './host/popup-demo/steps/host-1'; 
import { PopupDemoComponent2 } from './host/popup-demo/steps/host-2'; 
import { PopupDemoComponent3 } from './host/popup-demo/steps/host-3'; 
import { PopupDemoComponent4 } from './host/popup-demo/steps/host-4'; 
import { ContentProjectionDemoComponent } from './content-projection/content-projection-demo/content-projection-demo.component'; 
import { ContentTabsDemoComponent } from './tabs/content-tabs-demo/content-tabs-demo.component'; 
import { OnInitDemoComponent } from './lifecycle/on-init/on-init-demo.component'; 
import { OnChangesDemoComponent } from './lifecycle/on-changes/on-changes-demo.component'; 
import { DiffersDemoComponent } from './lifecycle/differs/differs-demo.component'; 
import { AllHooksDemoComponent } from './lifecycle/all-hooks/all-hooks-demo.component'; 
import { NgBookIfDemoComponent } from './templates/ng-book-if/ng-book-if-demo.component'; 
import { NgBookForDemoComponent } from './templates/ng-book-for/ng-book-for-demo.component'; 
import { OnPushDemoComponent } from './change-detection/on-push-demo/on-push-demo.component'; 
import { ObservablesDemoComponent } from './change-detection/observables-demo/observables-demo.component'; 

const examples: ExampleDef[] = [
  { label: 'Intro', name: 'Root', path: '', component: IntroComponent},
  { label: 'Styling', name: 'Styling', path: 'styling', component: StylingDemoComponent},
  { label: 'Modifyng the Host (Step 1)', name: 'Host1', path: 'host-step-1', component: PopupDemoComponent1, dev: true },
  { label: 'Modifyng the Host (Step 2)', name: 'Host2', path: 'host-step-2', component: PopupDemoComponent2, dev: true },
  { label: 'Modifyng the Host (Step 3)', name: 'Host3', path: 'host-step-3', component: PopupDemoComponent3, dev: true },
  { label: 'Modifyng the Host (Step 4)', name: 'Host4', path: 'host-step-4', component: PopupDemoComponent4, dev: true },
  { label: 'Popup  - Modifyng the Host', name: 'Host', path: 'host-final', component: PopupDemoComponent },
  { label: 'Content Projection', name: 'ContentProjection', path: 'content-projection', component: ContentProjectionDemoComponent },
  { label: 'Tabs - Component Querying', name: 'Tabs', path: 'tabs', component: ContentTabsDemoComponent },
  { label: 'Lifecycle 1 - OnInit / OnDestroy', name: 'Lifecycle1', path: 'lifecycle-1', component: OnInitDemoComponent },
  { label: 'Lifecycle 2 - OnChange', name: 'Lifecycle2', path: 'lifecycle-2', component: OnChangesDemoComponent },
  { label: 'Lifecycle 3 - DoCheck', name: 'Lifecycle3', path: 'lifecycle-3', component: DiffersDemoComponent },
  { label: 'Lifecycle - All Hooks', name: 'LifecycleAllHooks', path: 'lifecycle-all-hooks', component: AllHooksDemoComponent },
  { label: 'ngBookIf', name: 'NgBookIf', path: 'ng-book-if', component: NgBookIfDemoComponent },
  { label: 'ngBookFor', name: 'NgBookFor', path: 'ng-book-for', component: NgBookForDemoComponent },
  { label: 'Change Detection - OnPush', name: 'ChangeDetectionOnPush', path: 'change-detection-on-push', component: OnPushDemoComponent },
  { label: 'Change Detection - Observable', name: 'ChangeDetectionObservable', path: 'change-detection-observable', component: ObservablesDemoComponent },
  
  
];

const routes: Routes = [
  { path: '', component: IntroComponent, pathMatch: 'full'},
  { path: 'styling', component: StylingDemoComponent, pathMatch: 'full'},
  { path: 'host-step-1', component: PopupDemoComponent1, pathMatch: 'full'},
  { path: 'host-step-2', component: PopupDemoComponent2, pathMatch: 'full'},
  { path: 'host-step-3', component: PopupDemoComponent3, pathMatch: 'full'},
  { path: 'host-step-4', component: PopupDemoComponent4, pathMatch: 'full'},
  { path: 'host-final', component: PopupDemoComponent, pathMatch: 'full'},
  { path: 'content-projection', component: ContentProjectionDemoComponent, pathMatch: 'full'},
  { path: 'tabs', component: ContentTabsDemoComponent, pathMatch: 'full'},
  { path: 'lifecycle-1', component: OnInitDemoComponent, pathMatch: 'full'},
  { path: 'lifecycle-2', component: OnChangesDemoComponent, pathMatch: 'full'},
  { path: 'lifecycle-3', component: DiffersDemoComponent, pathMatch: 'full'},
  { path: 'lifecycle-all-hooks', component: AllHooksDemoComponent, pathMatch: 'full'},
  { path: 'ng-book-if', component: NgBookIfDemoComponent, pathMatch: 'full'},
  { path: 'ng-book-for', component: NgBookForDemoComponent, pathMatch: 'full'},
  { path: 'change-detection-on-push', component: OnPushDemoComponent, pathMatch: 'full'},
  { path: 'change-detection-observable', component: ObservablesDemoComponent, pathMatch: 'full'},
  
];

@NgModule({
  declarations: [
    AppComponent,
    IntroComponent,
    SidebarComponent,
    SidebarItemComponent
  ],
  imports: [
    BrowserModule,
    StylingModule,
    HostModule,
    TabsModule,
    ContentProjectionModule,
    LifecycleModule,
    TemplatesModule,
    ChangeDetectionModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    {provide: APP_BASE_HREF, useValue: '/'},
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    {provide:'ExampleDefs', useValue: examples}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
