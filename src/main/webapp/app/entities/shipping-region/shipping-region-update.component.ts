import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IShippingRegion } from 'app/shared/model/shipping-region.model';
import { ShippingRegionService } from './shipping-region.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer';
import { IShipping } from 'app/shared/model/shipping.model';
import { ShippingService } from 'app/entities/shipping';

@Component({
    selector: 'jhi-shipping-region-update',
    templateUrl: './shipping-region-update.component.html'
})
export class ShippingRegionUpdateComponent implements OnInit {
    shippingRegion: IShippingRegion;
    isSaving: boolean;

    customers: ICustomer[];

    shippings: IShipping[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected shippingRegionService: ShippingRegionService,
        protected customerService: CustomerService,
        protected shippingService: ShippingService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ shippingRegion }) => {
            this.shippingRegion = shippingRegion;
        });
        this.customerService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICustomer[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICustomer[]>) => response.body)
            )
            .subscribe((res: ICustomer[]) => (this.customers = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.shippingService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IShipping[]>) => mayBeOk.ok),
                map((response: HttpResponse<IShipping[]>) => response.body)
            )
            .subscribe((res: IShipping[]) => (this.shippings = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.shippingRegion.id !== undefined) {
            this.subscribeToSaveResponse(this.shippingRegionService.update(this.shippingRegion));
        } else {
            this.subscribeToSaveResponse(this.shippingRegionService.create(this.shippingRegion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingRegion>>) {
        result.subscribe((res: HttpResponse<IShippingRegion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCustomerById(index: number, item: ICustomer) {
        return item.id;
    }

    trackShippingById(index: number, item: IShipping) {
        return item.id;
    }
}
