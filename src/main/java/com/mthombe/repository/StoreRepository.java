package com.mthombe.repository;

import com.mthombe.modal.Store;
import com.mthombe.payload.dto.StoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long id);
}
