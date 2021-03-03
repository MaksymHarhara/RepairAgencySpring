package com.training.RepairAgency.service;

import com.training.RepairAgency.dto.RequestDTO;
import com.training.RepairAgency.entity.Request;
import com.training.RepairAgency.entity.User;
import com.training.RepairAgency.repository.RequestRepository;
import com.training.RepairAgency.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Request saveRequest(String request, String name, RequestDTO requestDTO) {
        return requestRepository.save(
                Request.builder()
                        .request(request)
                        .status("new")
                        .price(BigDecimal.valueOf(0.0))
                        .creator(name)
                        .data(requestDTO.getDate())
                        .creator_id(userRepository.findByEmail(name).orElse(null))
                        .build()
        );
    }

    public Optional<Request> saveRequest(Request request) {
        return Optional.ofNullable(requestRepository.save(request));
    }


    public Page<RequestDTO> getRequestsByCreator(String creator, String status, Pageable pageable) {


        return requestRepository.findByCreatorAndStatusNot(creator, status)
                .<Page<RequestDTO>>map(requests -> new PageImpl<>(requests.stream()
                .map(this::buildRequest)
                .collect(Collectors.toList())))
                .orElse(null);
    }

    private RequestDTO buildRequest(Request r) {
        return RequestDTO.builder()
                .request(r.getRequest())
                .id(r.getId())
                .status(r.getStatus())
                .reason(r.getReason())
                .price(r.getPrice())
                .build();
    }

    public Page<Request> getRequestsByStatus(String status, Pageable pageable) {
        return requestRepository.findByStatus(status, pageable);
    }

    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    public Request findRequestById(Long id) {
        return requestRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void updateStatusAndMasterById(String status, Long id, String master, String reason, BigDecimal price) {
        Request tempRequest = requestRepository.findById(id).orElseThrow(RuntimeException::new);
        requestRepository.save(Request.builder()
                .id(id)
                .creator(tempRequest.getCreator())
                .request(tempRequest.getRequest())
                .status(status)
                .reason(reason)
                .price(price)
                .master(userRepository.findByEmail(master).orElse(null))
                .build());
    }

    public void updateStatusById(String status, Long id) {
        requestRepository.updateStatusById(status, id);
    }

    public void updateStatusAndReasonById(String status, Long id, String reason) {
        requestRepository.updateStatusAndReasonById(status, id, reason);
    }

    public Page<Request> getRequestsByStatusAndEmail(String status, String email, Pageable pageable) {
        return requestRepository.findByStatusAndEmail(email, status, pageable);
    }

    public Optional<List<Request>> getByCreatorAndStatus(String creator, String status) {
        return requestRepository.findByCreatorAndStatus(creator, status);
    }

    public Page<Request> findByKeyword(String keyword, Pageable pageable) {
        return requestRepository.findByKeyword(keyword, pageable);
    }
}
