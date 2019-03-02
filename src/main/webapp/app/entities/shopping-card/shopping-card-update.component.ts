import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IShoppingCard } from 'app/shared/model/shopping-card.model';
import { ShoppingCardService } from './shopping-card.service';

@Component({
    selector: 'jhi-shopping-card-update',
    templateUrl: './shopping-card-update.component.html'
})
export class ShoppingCardUpdateComponent implements OnInit {
    shoppingCard: IShoppingCard;
    isSaving: boolean;
    addedOnDp: any;

    constructor(protected shoppingCardService: ShoppingCardService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ shoppingCard }) => {
            this.shoppingCard = shoppingCard;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.shoppingCard.id !== undefined) {
            this.subscribeToSaveResponse(this.shoppingCardService.update(this.shoppingCard));
        } else {
            this.subscribeToSaveResponse(this.shoppingCardService.create(this.shoppingCard));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IShoppingCard>>) {
        result.subscribe((res: HttpResponse<IShoppingCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
