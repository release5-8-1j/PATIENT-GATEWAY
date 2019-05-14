import { NgModule } from '@angular/core';

import { PatientgatewaySharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [PatientgatewaySharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [PatientgatewaySharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class PatientgatewaySharedCommonModule {}
