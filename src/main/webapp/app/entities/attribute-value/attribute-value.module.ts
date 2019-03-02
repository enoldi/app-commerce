import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppcommerceSharedModule } from 'app/shared';
import {
    AttributeValueComponent,
    AttributeValueDetailComponent,
    AttributeValueUpdateComponent,
    AttributeValueDeletePopupComponent,
    AttributeValueDeleteDialogComponent,
    attributeValueRoute,
    attributeValuePopupRoute
} from './';

const ENTITY_STATES = [...attributeValueRoute, ...attributeValuePopupRoute];

@NgModule({
    imports: [AppcommerceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttributeValueComponent,
        AttributeValueDetailComponent,
        AttributeValueUpdateComponent,
        AttributeValueDeleteDialogComponent,
        AttributeValueDeletePopupComponent
    ],
    entryComponents: [
        AttributeValueComponent,
        AttributeValueUpdateComponent,
        AttributeValueDeleteDialogComponent,
        AttributeValueDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppcommerceAttributeValueModule {}
