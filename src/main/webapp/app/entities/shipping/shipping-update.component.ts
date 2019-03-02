import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IShipping } from 'app/shared/model/shipping.model';
import { ShippingService } from './shipping.service';

@Component({
    selector: 'jhi-shipping-update',
    templateUrl: './shipping-update.component.html'
})
export class ShippingUpdateComponent implements OnInit {
    shipping: IShipping;
    isSaving: boolean;

    constructor(protected shippingService: ShippingService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ shipping }) => {
            this.shipping = shipping;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.shipping.id !== undefined) {
            this.subscribeToSaveResponse(this.shippingService.update(this.shipping));
        } else {
            this.subscribeToSaveResponse(this.shippingService.create(this.shipping));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IShipping>>) {
        result.subscribe((res: HttpResponse<IShipping>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
