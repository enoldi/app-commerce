import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppcommerceSharedModule } from 'app/shared';
import {
    ShippingComponent,
    ShippingDetailComponent,
    ShippingUpdateComponent,
    ShippingDeletePopupComponent,
    ShippingDeleteDialogComponent,
    shippingRoute,
    shippingPopupRoute
} from './';

const ENTITY_STATES = [...shippingRoute, ...shippingPopupRoute];

@NgModule({
    imports: [AppcommerceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ShippingComponent,
        ShippingDetailComponent,
        ShippingUpdateComponent,
        ShippingDeleteDialogComponent,
        ShippingDeletePopupComponent
    ],
    entryComponents: [ShippingComponent, ShippingUpdateComponent, ShippingDeleteDialogComponent, ShippingDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppcommerceShippingModule {}
