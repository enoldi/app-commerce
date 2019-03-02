import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShoppingCard } from 'app/shared/model/shopping-card.model';
import { ShoppingCardService } from './shopping-card.service';

@Component({
    selector: 'jhi-shopping-card-delete-dialog',
    templateUrl: './shopping-card-delete-dialog.component.html'
})
export class ShoppingCardDeleteDialogComponent {
    shoppingCard: IShoppingCard;

    constructor(
        protected shoppingCardService: ShoppingCardService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.shoppingCardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'shoppingCardListModification',
                content: 'Deleted an shoppingCard'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-shopping-card-delete-popup',
    template: ''
})
export class ShoppingCardDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ shoppingCard }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ShoppingCardDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.shoppingCard = shoppingCard;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/shopping-card', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/shopping-card', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
