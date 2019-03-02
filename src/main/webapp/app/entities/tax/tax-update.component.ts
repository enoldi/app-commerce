import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITax } from 'app/shared/model/tax.model';
import { TaxService } from './tax.service';

@Component({
    selector: 'jhi-tax-update',
    templateUrl: './tax-update.component.html'
})
export class TaxUpdateComponent implements OnInit {
    tax: ITax;
    isSaving: boolean;

    constructor(protected taxService: TaxService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tax }) => {
            this.tax = tax;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tax.id !== undefined) {
            this.subscribeToSaveResponse(this.taxService.update(this.tax));
        } else {
            this.subscribeToSaveResponse(this.taxService.create(this.tax));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITax>>) {
        result.subscribe((res: HttpResponse<ITax>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
