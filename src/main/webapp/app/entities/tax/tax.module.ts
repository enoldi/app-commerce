import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppcommerceSharedModule } from 'app/shared';
import {
    TaxComponent,
    TaxDetailComponent,
    TaxUpdateComponent,
    TaxDeletePopupComponent,
    TaxDeleteDialogComponent,
    taxRoute,
    taxPopupRoute
} from './';

const ENTITY_STATES = [...taxRoute, ...taxPopupRoute];

@NgModule({
    imports: [AppcommerceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TaxComponent, TaxDetailComponent, TaxUpdateComponent, TaxDeleteDialogComponent, TaxDeletePopupComponent],
    entryComponents: [TaxComponent, TaxUpdateComponent, TaxDeleteDialogComponent, TaxDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppcommerceTaxModule {}
