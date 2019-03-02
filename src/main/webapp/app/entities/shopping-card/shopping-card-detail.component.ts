import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShoppingCard } from 'app/shared/model/shopping-card.model';

@Component({
    selector: 'jhi-shopping-card-detail',
    templateUrl: './shopping-card-detail.component.html'
})
export class ShoppingCardDetailComponent implements OnInit {
    shoppingCard: IShoppingCard;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ shoppingCard }) => {
            this.shoppingCard = shoppingCard;
        });
    }

    previousState() {
        window.history.back();
    }
}
