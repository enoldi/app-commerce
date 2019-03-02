import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShippingRegion } from 'app/shared/model/shipping-region.model';

@Component({
    selector: 'jhi-shipping-region-detail',
    templateUrl: './shipping-region-detail.component.html'
})
export class ShippingRegionDetailComponent implements OnInit {
    shippingRegion: IShippingRegion;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ shippingRegion }) => {
            this.shippingRegion = shippingRegion;
        });
    }

    previousState() {
        window.history.back();
    }
}
