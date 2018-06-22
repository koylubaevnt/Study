import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentTabsDemoComponent } from './content-tabs-demo/content-tabs-demo.component';
import { ContentTabComponent } from './content-tabs-demo/content-tab.component';
import { ContentTabsetComponent } from './content-tabs-demo/content-tabset.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ContentTabsDemoComponent, ContentTabComponent, ContentTabsetComponent]
})
export class TabsModule { }
