import { NgModule } from '@angular/core';

import { AppcommerceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AppcommerceSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AppcommerceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AppcommerceSharedCommonModule {}
