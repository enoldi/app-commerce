import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShippingRegion } from 'app/shared/model/shipping-region.model';
import { ShippingRegionService } from './shipping-region.service';

@Component({
    selector: 'jhi-shipping-region-delete-dialog',
    templateUrl: './shipping-region-delete-dialog.component.html'
})
export class ShippingRegionDeleteDialogComponent {
    shippingRegion: IShippingRegion;

    constructor(
        protected shippingRegionService: ShippingRegionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.shippingRegionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'shippingRegionListModification',
                content: 'Deleted an shippingRegion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-shipping-region-delete-popup',
    template: ''
})
export class ShippingRegionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ shippingRegion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ShippingRegionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.shippingRegion = shippingRegion;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/shipping-region', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/shipping-region', { outlets: { popup: null } }]);
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
