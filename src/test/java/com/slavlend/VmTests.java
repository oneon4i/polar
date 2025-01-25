package com.slavlend;

import com.slavlend.Vm.*;
import com.slavlend.Vm.Instructions.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


// Тесты ВМ
public class VmTests {
    @Test
    public void VmTest0() {
        IceVm test = new IceVm();
        test.initStackForThread();
        test.push(123);
        assertEquals(test.pop(), 123);
    }

    @Test
    public void VmTest1() {
        IceVm test = new IceVm();
        test.initStackForThread();
        test.push(743);
        assertEquals(test.getStack().get().size(), 1);
        test.pop();
    }

    @Test
    public void VmTest2() {
        IceVm test = new IceVm();
        VmCode code = new VmCode();
        test.initStackForThread();
        VmVarContainer container = new VmVarContainer();
        code.startWrite(container);
        code.visitInstr(new VmInstrPush(new VmInAddr(-1), 123));
        code.endWrite();
        code.visitInstr(
                new VmInstrStore(
                    new VmInAddr(-1),
                        "a",
                        false,
                        container
                )
        );
        test.run(code, true);
        assertTrue(test.getVariables().has("a"));
    }

    @Test
    public void VmTest3() {
        IceVm test = new IceVm();
        IceVm.logger = new PolarLogger();
        VmCode code = new VmCode();
        test.initStackForThread();
        // функция
        VmFunction container = new VmFunction("fn", new ArrayList<>(), new VmInAddr(-1));
        test.getFunctions().set("fn", container);
        code.startWrite(container);
        // переменная
        VmVarContainer varContainer = new VmVarContainer();
        code.startWrite(varContainer);
        code.visitInstr(new VmInstrPush(new VmInAddr(-1), 123));
        code.endWrite();
        // сохранение переменной
        code.visitInstr(
                new VmInstrStore(
                        new VmInAddr(-1),
                        "a",
                        false,
                        varContainer
                )
        );
        // выход
        code.endWrite();
        code.visitInstr(new VmInstrCall(
                new VmInAddr(-1),
                "fn",
                new VmVarContainer(),
                false
        ));
        test.run(code, true);
        assertTrue(test.getFunctions().has("fn"));
        assertFalse(test.getVariables().has("a"));
    }

    @Test
    public void VmTest4() {
        IceVm test = new IceVm();
        VmCode code = new VmCode();
        test.initStackForThread();
        // класс
        VmClass clazz = new VmClass("fn", new ArrayList<>(), new VmInAddr(-1));
        test.getClasses().set("fn", clazz);
        code.startWrite(clazz);
        // функция
        VmFunction fn = new VmFunction("init", new ArrayList<>(), new VmInAddr(-1));
        clazz.getFunctions().set("init", fn);
        code.startWrite(fn);
        // код функции
        code.visitInstr(
                new VmInstrLoad(
                        new VmInAddr(-1),
                        "this",
                        false
                )
        );
        // переменная
        VmVarContainer varContainer = new VmVarContainer();
        code.startWrite(varContainer);
        varContainer.visitInstr(new VmInstrPush(new VmInAddr(-1), 123));
        code.endWrite();
        code.visitInstr(
                new VmInstrStore(
                        new VmInAddr(-1),
                        "a",
                        true,
                        varContainer
                )
        );
        code.endWrite();
        code.endWrite();
        VmVarContainer sVarContainer = new VmVarContainer();
        code.startWrite(sVarContainer);
        code.visitInstr(new VmInstrNewObj(new VmInAddr(-1), "fn", new VmVarContainer()));
        code.endWrite();
        code.visitInstr(
                new VmInstrStore(
                        new VmInAddr(-1),
                        "b",
                        false,
                        sVarContainer
                )
        );
        test.run(code, true);
        assertFalse(test.getVariables().has("a"));
        assertNotEquals(
                ((VmObj)test.getVariables().lookup(new VmInAddr(-1), "b"))
                        .getScope().lookup(new VmInAddr(-1), "a"), null);
    }
}
