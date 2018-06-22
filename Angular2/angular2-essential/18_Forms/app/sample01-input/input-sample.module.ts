import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { EventSampleComponent, EventObjectSampleComponent, TemplateRefSampleComponent, KeyEnterSampleComponent, BlurSampleComponent, MyListSampleComponent } from "./index";

@NgModule({
    imports: [CommonModule],
    declarations: [
        EventSampleComponent,
        EventObjectSampleComponent,
        TemplateRefSampleComponent,
        KeyEnterSampleComponent,
        BlurSampleComponent,
        MyListSampleComponent,
    ]
})
export class InputSampleModule {}