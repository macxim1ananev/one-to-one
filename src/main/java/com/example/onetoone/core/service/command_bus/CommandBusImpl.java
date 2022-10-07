package com.example.onetoone.core.service.command_bus;

import com.example.onetoone.core.service.common.Interactor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unchecked")
@Component
@Slf4j
class CommandBusImpl implements CommandBus {

    private final Map<Class<?>, Interactor> interactors;

    public CommandBusImpl(List<Interactor> interactorList) {
        interactors = interactorList.stream()
                .collect(toMap(this::getInteractorCommandClass, i -> i));
    }

    @Override
    public <C extends Command, R> R execute(C command) {
        Optional<Class<?>> commandClazz = Optional.ofNullable(command)
                .map(C::getClass);
        return commandClazz.map(clazz -> (Interactor<C, R>) interactors.get(clazz))
                .orElseThrow(() -> new RuntimeException("Can not handle " + commandClazz.map(Class::getName).orElse("N/A")))
                .execute(command);
    }

    private Class<?> getInteractorCommandClass(Interactor interactor) {
        try {
            return Stream.of(AopUtils.isAopProxy(interactor) ? ((Advised) interactor).getTargetClass()
                            : interactor.getClass())
                    .filter(Objects::nonNull)
                    .map(Class::getGenericInterfaces)
                    .flatMap(Stream::of)
                    .filter(type -> type instanceof ParameterizedType)
                    .map(type -> (ParameterizedType) type)
                    .findFirst()
                    .map(ParameterizedType::getActualTypeArguments)
                    .stream()
                    .map(actualTypeArgs -> (Class<?>) actualTypeArgs[0])
                    .findFirst()
                    .get();
        } catch (Exception e) {
            log.error("Cannot load one of interactors", e);
            throw new RuntimeException("Cannot load one of interactors, no generic parameter found", e);
        }
    }
}