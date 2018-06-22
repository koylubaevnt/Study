import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import {
    Sample1HostComponent,
    Sample2HostComponent,
    Sample3HostComponent,
    Sample4HostComponent,
    Sample1Component,
    Sample2Component,
    Sample3Component,
    Sample4Component
} from "./index";

@NgModule({
    imports: [CommonModule],
    declarations: [
        Sample1HostComponent,
        Sample2HostComponent,
        Sample3HostComponent,
        Sample4HostComponent,
        Sample1Component,
        Sample2Component,
        Sample3Component,
        Sample4Component
    ]
})
export class LifecycleSampleModule {

}