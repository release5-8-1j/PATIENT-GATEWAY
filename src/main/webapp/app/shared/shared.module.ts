import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { PatientgatewaySharedLibsModule, PatientgatewaySharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [PatientgatewaySharedLibsModule, PatientgatewaySharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [PatientgatewaySharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PatientgatewaySharedModule {
    static forRoot() {
        return {
            ngModule: PatientgatewaySharedModule
        };
    }
}
